package com.fintech.orion.resource.support;

/**
 * Created by TharinduMP on 3/29/2017.
 */
public interface ResourceSupport {

    boolean isSupportExtension(String extension);

    boolean isSupportFileSize(String extension, long fileSizeInKB);

}
