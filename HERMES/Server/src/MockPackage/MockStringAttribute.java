package MockPackage;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
public @interface MockStringAttribute { String[] value();}
