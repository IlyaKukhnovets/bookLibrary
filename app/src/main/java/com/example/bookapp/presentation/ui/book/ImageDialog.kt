package com.example.bookapp.presentation.ui.book

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.bookapp.R
import com.example.bookapp.databinding.DialogBookPreviewBinding
import com.example.bookapp.presentation.ui.base.KEY_ARGS
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ImageDialog : BottomSheetDialogFragment() {

    private val binding by viewBinding(DialogBookPreviewBinding::bind)
    private val image: String by lazy {
        arguments?.getString(KEY_ARGS) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_book_preview, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            setupFullHeight(dialog as BottomSheetDialog)
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(view).load(image).into(binding.bookPreview)
        binding.ivClose.setOnClickListener {
            dialog?.dismiss()
        }
    }

    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        (bottomSheetDialog.findViewById<View>(
            com.google.android.material.R.id.design_bottom_sheet
        ) as? FrameLayout)?.let { bottomSheet ->
            val behavior = BottomSheetBehavior.from(bottomSheet)
            val layoutParams = bottomSheet.layoutParams
            val displayMetrics = requireContext().resources.displayMetrics
            val windowHeight = displayMetrics.heightPixels - 56
            if (layoutParams != null) layoutParams.height = windowHeight
            bottomSheet.layoutParams = layoutParams
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }


}