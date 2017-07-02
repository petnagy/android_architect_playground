package com.playground.android_architect_playground.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by petnagy on 2017. 07. 02..
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
