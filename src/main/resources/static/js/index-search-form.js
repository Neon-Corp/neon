$.post("/brand/search")
    .done(data => {
        $('input#brand').autocomplete({
            minChars: 0,
            maxHeight: 300,
            lookup: data.suggestions,
            showNoSuggestionNotice: true,
            noSuggestionNotice: "Marca não encontrada",
            onSelect: function(brandSuggestion) {
                $('input#deviceBrandInput').val(brandSuggestion.data.brandID);
                $.post("/model/search", brandSuggestion.data)
                    .done(data => {
                        $('input#modelAutocomplete').autocomplete({
                            minChars: 0,
                            maxHeight: 300,
                            lookup: data.suggestions,
                            showNoSuggestionNotice: true,
                            noSuggestionNotice: "Modelo não encontrado",
                            onSelect: function(suggestion) {
                                $('input#deviceModelSearchInput').val(suggestion.value)
                            }
                        });
                    })
                    .fail(data => {
                        console.error(data);
                    });
            }
        });
    })
    .fail(data => {
        console.error(data);
    });