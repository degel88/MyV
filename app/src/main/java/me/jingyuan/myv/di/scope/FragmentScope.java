package me.jingyuan.myv.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by degel
 */

@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
