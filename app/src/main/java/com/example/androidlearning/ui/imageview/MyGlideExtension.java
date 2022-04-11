package com.example.androidlearning.ui.imageview;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.RequestOptions;

@GlideExtension
public class MyGlideExtension {
    private MyGlideExtension() {
    }

//    /**
//     * 全局统一配置
//     *
//     * @param options 配置
//     */
//    @GlideOption
//    public static void injectOptions(RequestOptions options) {
//        options.placeholder(R.mipmap.loading)
//                .error(R.mipmap.loader_error)
//                .circleCrop();
//    }
}
