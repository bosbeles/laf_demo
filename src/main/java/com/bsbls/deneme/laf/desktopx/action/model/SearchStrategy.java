package com.bsbls.deneme.laf.desktopx.action.model;

import java.util.Collection;
import java.util.List;

public interface SearchStrategy {

    List<ActionWrapper> search(Collection<ActionWrapper> list, String keyword);

}
