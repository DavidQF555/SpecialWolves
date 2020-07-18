package com.david.specialwolves.wolves;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CustomWolf {

	public String name();

}
