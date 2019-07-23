package me.jingyuan.myv.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * desc：me.jingyuan.myv.di.qualifier
 *
 * @author：degel on 2019-07-22 16:49
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface GankUrl {
}
