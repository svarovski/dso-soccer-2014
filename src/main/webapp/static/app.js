$(document).ready(function() {
    var resources = ($.cookie("dso-soccer-resources") || "0:0:0:0:0:0:0:0:0:0:0:0:0:0").split(":");

    $("#condition")
        .submit(function(e) {
            $(this).find("input").each(function(i, o) { resources[i] = +$(o).val(); });
            $.cookie("dso-soccer-resources", resources.join(":"), { "expires": 365 });
        })
        .find("input").each(function(i, o) { $(o).val(resources[i]); });

    $("#save_remainder").click(function(e) {
        $(".remainder").each(function(i, o) { resources[i] = +$(o).text().trim(); });
        $.cookie("dso-soccer-resources", resources.join(":"), { "expires": 365 });
    });

    var $entities = $(".entity_list");
    if($entities) {
        $.get("/match/links", function(data) {
            $entities.find("tr[data-entity]").on("mouseover", function(e) {
                $entities.find("tr.highlight").removeClass("highlight");
                $(this).addClass("highlight");
                $.each(data[$(this).attr("data-entity")], function(i, o) {
                    $entities.find("tr[data-entity="+o+"]").addClass("highlight");
                });
            })
        });
    }
});
