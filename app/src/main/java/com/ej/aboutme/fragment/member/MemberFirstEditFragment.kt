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
import com.ej.aboutme.MainActivity
import com.ej.aboutme.databinding.FragmentMemberFirstEditBinding
import com.ej.aboutme.dto.request.MemberUpdateDto
import com.ej.aboutme.fragment.navi.MyHomeEditFragment
import com.ej.aboutme.preferences.QueryPreferences
import com.ej.aboutme.viewmodel.MemberViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import okhttp3.MultipartBody
import java.io.File
import java.io.FileOutputStream

class MemberFirstEditFragment : Fragment() {

    val act : MainActivity by lazy { activity as MainActivity }
    val parentFragment : MyHomeEditFragment by lazy {getParentFragment() as MyHomeEditFragment }
    val memberViewModel : MemberViewModel by lazy { parentFragment.memberViewModel}
    val queryPreferences : QueryPreferences by lazy { QueryPreferences() }
    var uploadImage : Bitmap? = null

    lateinit var memberFirstEditFragmentBinding : FragmentMemberFirstEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        memberFirstEditFragmentBinding = FragmentMemberFirstEditBinding.inflate(inflater)
        val memberInfo = memberViewModel.memberTotalInfo.value
        memberFirstEditFragmentBinding.memberEditName.editText?.setText(memberInfo?.name)
        memberFirstEditFragmentBinding.memberEditJob.editText?.setText(memberInfo?.job)
        memberFirstEditFragmentBinding.memberEditPhone.editText?.setText(memberInfo?.phone)
        memberFirstEditFragmentBinding.memberEditContent.editText?.setText(memberInfo?.content)

        val profileImage = memberFirstEditFragmentBinding.profileEditImage
        val tagTextView = memberFirstEditFragmentBinding.groupAddText
        val tagAddBtn = memberFirstEditFragmentBinding.tagAddBtn
        val tagGroup = memberFirstEditFragmentBinding.tagGroup

        profileImage.setOnClickListener {
            // 갤러리 들어가기
            val albumIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            albumIntent.type = "image/*"

            val mimeType = arrayOf("image/*")
            albumIntent.putExtra(Intent.EXTRA_MIME_TYPES,mimeType)
            startActivityForResult(albumIntent,2)

            true
        }

        tagAddBtn.setOnClickListener {
            val tagInputStr = tagTextView.editText?.text.toString()
            addTag(tagGroup,tagInputStr)
            tagTextView.editText?.setText("")
        }


        for (tagStr in memberInfo!!.tag) {
            addTag(tagGroup, tagStr)
        }




        return memberFirstEditFragmentBinding.root
    }

    override fun onResume() {
        super.onResume()

        act.binding.floatingActionButton.setOnClickListener{
            Log.d("fab","mfef")
            val name = memberFirstEditFragmentBinding.memberEditName.editText?.text.toString()
            val job = memberFirstEditFragmentBinding.memberEditJob.editText?.text.toString()
            val phone = memberFirstEditFragmentBinding.memberEditPhone.editText?.text.toString()
            val content = memberFirstEditFragmentBinding.memberEditContent.editText?.text.toString()

            val tagStrList = mutableListOf<String>()
            val tagGroup = memberFirstEditFragmentBinding.tagGroup as ChipGroup
            val tagCount = tagGroup.childCount
            for (i in 0 until tagCount) {
                val chip = tagGroup.get(i) as Chip

                tagStrList.add(chip.text.toString())
            }
            val memberUpdateDto = MemberUpdateDto(name,job,phone, content,tagStrList)

            val memberId = queryPreferences.getUserId(act)

//            myHomeViewModel.getMemberTotalInfo(memberId)
            var file : File? = null
            var uri : Uri? = null
            if(uploadImage !=null){
                val filePath = requireContext().getExternalFilesDir(null).toString()
                val fileName = "/temp_${System.currentTimeMillis()}.jpg"
                val picPath= "$filePath/$fileName"
                file = File(picPath)
                val fos = FileOutputStream(picPath)
                uploadImage?.compress(Bitmap.CompressFormat.JPEG, 100 , fos)

            }
            memberViewModel.updateMember(memberId,memberUpdateDto,file)



            act.setFragment("my_home")
            return@setOnClickListener
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
                            memberFirstEditFragmentBinding.profileEditImage.setImageBitmap(uploadImage)
//                            memberFirstEditFragmentBinding.profileEditImage.visibility = View.VISIBLE

                        } else {
                            val cursor = activity?.contentResolver?.query(uri,null,null,null,null)
                            if (cursor != null) {
                                cursor.moveToNext()
                                // 이미지 경로를 가져온다
                                val index = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                                val source = cursor.getString(index)
                                uploadImage = BitmapFactory.decodeFile(source)
                                memberFirstEditFragmentBinding.profileEditImage.setImageBitmap(uploadImage)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun addTag(tagGroup: ChipGroup, tagStr: String) {
        tagGroup.addView(Chip(requireContext()).apply {
            text = tagStr
            isCloseIconVisible = true// x 버튼 보이게하기
            // 클릭시 삭제 리스너
            setOnCloseIconClickListener {
                tagGroup.removeView(this)
            }
        })
    }


}