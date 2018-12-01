$.post("/brand/search")
.done(data => {
    $('input#brand').autocomplete({
        minChars: 0,
        maxHeight: 300,
        lookup: data.suggestions,
        showNoSuggestionNotice: true,
        noSuggestionNotice: "Marca não encontrada",
        onSelect: function(brandSuggestion) {
            $.post("/model/search", brandSuggestion.data)
            .done(data => {
                $('input#modelAutocomplete').autocomplete({
                    minChars: 0,
                    maxHeight: 300,
                    lookup: data.suggestions,
                    showNoSuggestionNotice: true,
                    noSuggestionNotice: "Modelo não encontrado",
                    onSelect: function(suggestion) {
                        $('input#model').val(suggestion.data.brandID)
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