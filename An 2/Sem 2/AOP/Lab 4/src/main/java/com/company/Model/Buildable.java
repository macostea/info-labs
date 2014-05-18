package com.company.Model;

import java.util.Map;

/**
 * Created by C.Mihai on 14/03/14.
 */
public interface Buildable extends HasId {
    public void buildObjectFromMap(Map<String, String> map);
}
