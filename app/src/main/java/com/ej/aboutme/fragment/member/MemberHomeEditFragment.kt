package com.ej.aboutme.fragment.member

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.ej.aboutme.MainActivity
import com.ej.aboutme.R
import com.ej.aboutme.adapter.CardEditAdapter
import com.ej.aboutme.databinding.FragmentMemberHomeEditBinding
import com.ej.aboutme.viewmodel.MainViewModel
import com.ej.aboutme.dto.request.MemberUpdateDto
import com.ej.aboutme.dto.response.MemberInfoDto
import com.ej.aboutme.fragment.dialog.MemberInfoEditFragmentDialog
import com.ej.aboutme.preferences.QueryPreferences
import com.ej.aboutme.util.ServerInfo
import com.ej.aboutme.viewmodel.GroupViweModel
import com.ej.aboutme.viewmodel.MemberViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class MemberHomeEditFragment : Fragment() {

    lateinit var binding: FragmentMemberHomeEditBinding
    val act : MainActivity by lazy { activity as MainActivity }
    private val memberViewModel: MemberViewModel by activityViewModels()
    private val groupViewModel: GroupViweModel by activityViewModels()
    val queryPreferences : QueryPreferences by lazy { QueryPreferences() }
    var uploadImage : Bitmap? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        myHomeEditFragmentBinding = FragmentMyHomeEditBinding.inflate(inflater)
        binding = FragmentMemberHomeEditBinding.inflate(LayoutInflater.from(container!!.context),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileEditImage.setOnClickListener {
            choicGallaryImage()
        }

        binding.tagAddBtn.setOnClickListener {
            tagSet()
        }

        drawUi()
    }
    override fun onResume() {
        super.onResume()
        updateMember()
    }


    private fun drawUi() {
        val memberTotalInfo = memberViewModel.memberTotalInfo.value
        if (memberTotalInfo?.image != "") {
            val imageFullUrl = "${ServerInfo.SERVER_IMAGE}${memberTotalInfo?.image}"
            Glide.with(act).load(imageFullUrl).error(R.drawable.empty_img)
                .into(binding.profileEditImage);
        }
        binding.memberEditName.editText?.setText(memberTotalInfo?.name)
        binding.memberEditJob.editText?.setText(memberTotalInfo?.job)
        binding.memberEditPhone.editText?.setText(memberTotalInfo?.phone)
        binding.memberEditContent.editText?.setText(memberTotalInfo?.content)


        for (tagStr in memberTotalInfo!!.tag) {
            addTag(tagStr)
        }

        val funCardVal: (MemberInfoDto) -> Unit = { memberInfo -> cardEditDialog(memberInfo) }
        val cardEditAdapter = CardEditAdapter(funCardVal)

        binding.cardEditRecycler.apply {
            adapter = cardEditAdapter
            layoutManager = GridLayoutManager(requireContext(), 4)
            isNestedScrollingEnabled = false
        }
        val memberInfoList = memberViewModel.memberTotalInfo.value!!.memberInfo
        cardEditAdapter.submitList(memberInfoList)

        memberViewModel.memberInfo.observe(viewLifecycleOwner) {
            cardEditAdapter.submitList(it)
        }
    }

    private fun tagSet() {
        val tagInputStr = binding.groupAddText.editText?.text.toString()
        addTag(tagInputStr)
        binding.groupAddText.editText?.setText("")
    }

    private fun choicGallaryImage() {
        // 갤러리 들어가기
        val albumIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        albumIntent.type = "image/*"

        val mimeType = arrayOf("image/*")
        albumIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
        startActivityForResult(albumIntent, 2)
    }



    private fun updateMember() {
        act.binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_save_24)
        act.binding.floatingActionButton.setOnClickListener {

            val name = binding.memberEditName.editText?.text.toString()
            val job = binding.memberEditJob.editText?.text.toString()
            val phone = binding.memberEditPhone.editText?.text.toString()
            val content = binding.memberEditContent.editText?.text.toString()

            val tagStrList = mutableListOf<String>()
            val tagGroup = binding.tagGroup as ChipGroup
            val tagCount = tagGroup.childCount
            for (i in 0 until tagCount) {
                val chip = tagGroup.get(i) as Chip

                tagStrList.add(chip.text.toString())
            }
            val memberUpdateDto = MemberUpdateDto(name, job, phone, content, tagStrList)

            val memberId = queryPreferences.getUserId(act)

    //            myHomeViewModel.getMemberTotalInfo(memberId)
            var file: File? = null
            var uri: Uri? = null
            if (uploadImage != null) {
                val filePath = requireContext().getExternalFilesDir(null).toString()
                val fileName = "/temp_${System.currentTimeMillis()}.jpg"
                val picPath = "$filePath/$fileName"
                file = File(picPath)
                val fos = FileOutputStream(picPath)
                uploadImage?.compress(Bitmap.CompressFormat.JPEG, 100, fos)

            }

            memberViewModel.updateMemberCheck.observe(viewLifecycleOwner) {
                act.setFragment(MemberHomeFragment.TAG)
            }
            memberViewModel.updateMember(memberId, memberUpdateDto, file)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            2 -> {
                if (resultCode == Activity.RESULT_OK) {
                    // 선택한 이미지에 접근할 수 있는 uri
                    val uri = data?.data

                    if (uri != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            val source = ImageDecoder.createSource(activity?.contentResolver!!, uri)
                            uploadImage = ImageDecoder.decodeBitmap(source)
                            binding.profileEditImage.setImageBitmap(uploadImage)
//                            memberFirstEditFragmentBinding.profileEditImage.visibility = View.VISIBLE

                        } else {
                            val cursor = activity?.contentResolver?.query(uri,null,null,null,null)
                            if (cursor != null) {
                                cursor.moveToNext()
                                // 이미지 경로를 가져온다
                                val index = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                                val source = cursor.getString(index)
                                uploadImage = BitmapFactory.decodeFile(source)
                                binding.profileEditImage.setImageBitmap(uploadImage)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun addTag( tagStr: String) {
        binding.tagGroup.addView(Chip(requireContext()).apply {
            text = tagStr
            isCloseIconVisible = true// x 버튼 보이게하기
            // 클릭시 삭제 리스너
            setOnCloseIconClickListener {
                binding.tagGroup.removeView(this)
            }
        })
    }

    private fun cardEditDialog(memberInfoDto : MemberInfoDto){
        val dialog = MemberInfoEditFragmentDialog(memberInfoDto)
        dialog.show(
            act.supportFragmentManager,"상세정보!"
        )
    }

    companion object {
        val TAG = "MemberHomeEditFragment"
        fun newInstance(): MemberHomeEditFragment {
            return MemberHomeEditFragment()
        }
    }
}