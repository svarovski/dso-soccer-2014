$(document).ready(function() {
    var resources = ($.cookie("dso-soccer-resources") || "0:0:0:0:0:0:0:0:0:0:0:0:0:0").split(":");

    $("#condition")
        .submit(function(e) {
            $(form).find("input").each(function(i, o) { resources[i] = $(o).val(); });
            $.cookie("dso-soccer-resources", resources.join(":"), { "expires": 365});
        })
        .find("input").each(function(i, o) { $(o).val(resources[i]); });

    $("#save_remainder").click(function(e) {
        $(".remainder").each(function(i, o) { resources[i] = +$(o).text().trim(); });
        $.cookie("dso-soccer-resources", resources.join(":"), { "expires": 365});
    });
});
