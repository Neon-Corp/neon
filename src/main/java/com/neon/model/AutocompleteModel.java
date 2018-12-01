package com.neon.model;

import java.util.ArrayList;
import java.util.List;

public class AutocompleteModel {

    private List<Suggestion> suggestions;

    public AutocompleteModel() {
        suggestions = new ArrayList<>();
    }

    public void addSuggestion(Integer modelID, String modelName) {
        suggestions.add(new Suggestion(modelID, modelName));
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

        public Suggestion(Integer modelID, String modelName) {
            this.value = modelName;
            this.data = new Data();
            data.setModelID(modelID);
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
            private Integer modelID;

            public Integer getModelID() {
                return modelID;
            }

            public void setModelID(Integer modelID) {
                this.modelID = modelID;
            }
        }
    }
}
