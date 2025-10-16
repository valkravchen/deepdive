package com.deepdive.collection;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SearchHistory {
    private Set<String> searchHistory = new LinkedHashSet<String>();

    public boolean addSearch(String query) {
        if (query == null || query.trim().isEmpty()) {
            return false;
        }
        return searchHistory.add(query.trim().toLowerCase());
    }

    public List<String> getHistory() {
        return new ArrayList<>(searchHistory);
    }

    public List<String> getRecentSearches(int count) {
        if (count <= 0) {
            return new ArrayList<>();
        }
        List<String> history = getHistory();
        int size = history.size();

        if (count >= size) {
            return history;
        }
        return new ArrayList<>(history.subList(size - count, size));
    }


    public void clear() {
        searchHistory.clear();
    }

    public int size() {
        return searchHistory.size();
    }
}
