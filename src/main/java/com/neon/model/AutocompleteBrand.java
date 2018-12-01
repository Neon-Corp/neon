package com.neon.model;

import java.util.ArrayList;
import java.util.List;

public class AutocompleteBrand {

    private List<Suggestion> suggestions;

    public AutocompleteBrand() {
        suggestions = new ArrayList<>();
    }

    public void addSuggestion(Integer brandID, String brandName) {
        suggestions.add(new Suggestion(brandID, brandName));
    }

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

    class Suggestion {
        private String value;
        private Data data;

        public Suggestion(Integer brandID, String brandName) {
            this.value = brandName;
            this.data = new Data();
            data.setBrandID(brandID);
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        class Data {
            private Integer brandID;

            public Integer getBrandID() {
                return brandID;
            }

            public void setBrandID(Integer brandID) {
                this.brandID = brandID;
            }
        }
    }
}
