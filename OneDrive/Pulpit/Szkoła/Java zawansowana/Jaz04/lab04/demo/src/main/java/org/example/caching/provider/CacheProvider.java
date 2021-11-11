package org.example.caching.provider;

import org.example.model.Dictionary;

import java.util.ArrayList;

public interface CacheProvider {
     ArrayList<Dictionary> provide();
}
