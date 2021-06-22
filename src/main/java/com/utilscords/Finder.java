package com.utilscords;

import java.util.List;

interface Finder {

    List<String> find(String directory, String textToFind);

    List<String> findList(String directory, String textToFind);
}
