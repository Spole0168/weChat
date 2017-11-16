/*==================
 * 异步请求时加载效果
====================*/

zunmi.loading = function (e) {
    var shield = document.createElement("DIV");
    shield.id = "zunmishield";
    shield.style.height = document.documentElement.scrollHeight + "px";
    shield.style.filter = "alpha(opacity=0)";
    shield.style.opacity = 0;
    document.body.appendChild(shield);

    zunmi.setOpacity = function (obj, opacity) {
        if (opacity >= 1) opacity = opacity / 100;
        try { obj.style.opacity = opacity; } catch (err) { }
        try {
            if (obj.filters.length > 0 && obj.filters("alpha")) {
                obj.filters("alpha").opacity = opacity * 150;
            } else {
                obj.style.filter = "alpha(opacity=\"" + (opacity * 150) + "\")";
            }
        } catch (err) { }
    };
    var c = 0;
    zunmi.doAlpha = function () {
        c += 2;
        if (c > 20) { clearInterval(ad); return 0; }
        zunmi.setOpacity(shield, c);
    };
    //暂时不用遮罩效果
    var ad = setInterval("zunmi.doAlpha()", 1);
   
    var load = document.createElement("div");
    load.id = "zunmiload";
    var pos = $(e).position();
    load.style.left = pos.left + "px";
    load.style.top = pos.top + "px";
    load.style.width = e.offsetWidth - 11 + "px";
    load.style.height = e.offsetHeight - 5 + "px";
    document.body.appendChild(load);

    zunmi.setLoad = function (len) {
        if ($("#zunmiload").length == 0) return;
        var s = "";
        for (var i = 0; i < len; i++) s += " .";
        $("#zunmiload").html("Loading" + s);
        if (len == 6) len = 0; len++;
        setTimeout("zunmi.setLoad(" + len + ")", 400);
    };
    this.setLoad(6);
};
zunmi.loaded = function () {
    if ($("#zunmishield").length > 0) {
        document.body.removeChild($("#zunmishield")[0]);
    }
    if ($("#zunmiload").length > 0) {
        document.body.removeChild($("#zunmiload")[0]);
    }
};