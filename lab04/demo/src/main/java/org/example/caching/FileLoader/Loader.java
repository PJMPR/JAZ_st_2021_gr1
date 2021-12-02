package org.example.caching.FileLoader;


import org.example.model.Dictionary;

import java.util.List;

public interface Loader {
    List<Dictionary> provide();
}
