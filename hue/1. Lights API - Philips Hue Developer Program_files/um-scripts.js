jQuery(document).ready(function(){jQuery(document).on("click",".um-dropdown a",function(e){return!1}),jQuery(document).on("click",".um-dropdown a.real_url",function(e){window.location=jQuery(this).attr("href")}),jQuery(document).on("click",".um-trigger-menu-on-click",function(e){return jQuery(".um-dropdown").hide(),menu=jQuery(this).find(".um-dropdown"),menu.show(),!1}),jQuery(document).on("click",".um-dropdown-hide",function(e){UM_hide_menus()}),jQuery(document).on("click","a.um-manual-trigger",function(){var e=jQuery(this).attr("data-child"),t=jQuery(this).attr("data-parent");jQuery(this).parents(t).find(e).trigger("click")}),jQuery(".um-tip-n").tipsy({gravity:"n",opacity:1,live:"a.live",offset:3}),jQuery(".um-tip-w").tipsy({gravity:"w",opacity:1,live:"a.live",offset:3}),jQuery(".um-tip-e").tipsy({gravity:"e",opacity:1,live:"a.live",offset:3}),jQuery(".um-tip-s").tipsy({gravity:"s",opacity:1,live:"a.live",offset:3}),jQuery(document).on("change",".um-field-area input[type=radio]",function(){var e=jQuery(this).parents(".um-field-area"),t=jQuery(this).parents("label");e.find(".um-field-radio").removeClass("active"),e.find(".um-field-radio").find("i").removeAttr("class").addClass("um-icon-android-radio-button-off"),t.addClass("active"),t.find("i").removeAttr("class").addClass("um-icon-android-radio-button-on")}),jQuery(document).on("change",".um-field-area input[type=checkbox]",function(){jQuery(this).parents(".um-field-area");var e=jQuery(this).parents("label");e.hasClass("active")?(e.removeClass("active"),e.find("i").removeAttr("class").addClass("um-icon-android-checkbox-outline-blank")):(e.addClass("active"),e.find("i").removeAttr("class").addClass("um-icon-android-checkbox-outline"))}),jQuery(".um-datepicker").each(function(){if(elem=jQuery(this),""!=elem.attr("data-disabled_weekdays"))var e=JSON.parse(elem.attr("data-disabled_weekdays"));else e=!1;var t=elem.attr("data-years"),a=elem.attr("data-date_min"),i=elem.attr("data-date_max"),r=a.split(","),n=i.split(","),u=r.length?new Date(r):null,o=r.length?new Date(n):null;if(u&&"Invalid Date"==u.toString()&&3==r.length){var s=r[1]+"/"+r[2]+"/"+r[0];u=new Date(Date.parse(s))}if(o&&"Invalid Date"==o.toString()&&3==n.length){var d=n[1]+"/"+n[2]+"/"+n[0];o=new Date(Date.parse(d))}elem.pickadate({selectYears:t,min:u,max:o,disable:e,format:elem.attr("data-format"),formatSubmit:"yyyy/mm/dd",hiddenName:!0,onOpen:function(){elem.blur()},onClose:function(){elem.blur()}})}),jQuery(".um-timepicker").each(function(){elem=jQuery(this),elem.pickatime({format:elem.attr("data-format"),interval:parseInt(elem.attr("data-intervals")),formatSubmit:"HH:i",hiddenName:!0,onOpen:function(){elem.blur()},onClose:function(){elem.blur()}})}),jQuery(".um-rating").um_raty({half:!1,starType:"i",number:function(){return jQuery(this).attr("data-number")},score:function(){return jQuery(this).attr("data-score")},scoreName:function(){return jQuery(this).attr("data-key")},hints:!1,click:function(e,t){live_field=this.id,live_value=e,um_apply_conditions(jQuery(this),!1)}}),jQuery(".um-rating-readonly").um_raty({half:!1,starType:"i",number:function(){return jQuery(this).attr("data-number")},score:function(){return jQuery(this).attr("data-score")},scoreName:function(){return jQuery(this).attr("data-key")},hints:!1,readOnly:!0}),jQuery(document).on("click",".um .um-single-image-preview a.cancel",function(e){e.preventDefault();var t=jQuery(this).parents(".um-field"),a=jQuery(this).parents(".um-field").find(".um-single-image-preview img").attr("src");return t.find(".um-single-image-preview img").attr("src",""),t.find(".um-single-image-preview").hide(),t.find(".um-btn-auto-width").html("Upload"),t.find("input[type=hidden]").val("empty_file"),jQuery.ajax({url:wp.ajax.settings.url,type:"post",data:{action:"um_remove_file",src:a,nonce:um_scripts.nonce}}),!1}),jQuery(document).on("click",".um .um-single-file-preview a.cancel",function(e){e.preventDefault();var t=jQuery(this).parents(".um-field"),a=jQuery(this).parents(".um-field").find(".um-single-fileinfo a").attr("href");return t.find(".um-single-file-preview").hide(),t.find(".um-btn-auto-width").html("Upload"),t.find("input[type=hidden]").val("empty_file"),jQuery.ajax({url:wp.ajax.settings.url,type:"post",data:{action:"um_remove_file",src:a,nonce:um_scripts.nonce}}),!1}),jQuery(".um-s1,.um-s2").css({display:"block"}),0<jQuery(".um-s1").length&&jQuery(".um-s1").each(function(){var e=jQuery(this);""===e.val()&&e.attr("data-default")&&e.val(e.attr("data-default"))}),jQuery(".um-s1").select2({allowClear:!0}),jQuery(".um-s2").select2({allowClear:!1,minimumResultsForSearch:10}),jQuery(document).on("click",".um-field-group-head:not(.disabled)",function(){var e=jQuery(this).parents(".um-field-group"),t=e.data("max_entries");e.find(".um-field-group-body").is(":hidden")?e.find(".um-field-group-body").show():e.find(".um-field-group-body:first").clone().appendTo(e),increase_id=0,e.find(".um-field-group-body").each(function(){increase_id++,jQuery(this).find("input").each(function(){var e=jQuery(this);e.attr("id",e.data("key")+"-"+increase_id),e.attr("name",e.data("key")+"-"+increase_id),e.parent().parent().find("label").attr("for",e.data("key")+"-"+increase_id)})}),0<t&&e.find(".um-field-group-body").length==t&&jQuery(this).addClass("disabled")}),jQuery(document).on("click",".um-field-group-cancel",function(e){e.preventDefault();var t=jQuery(this).parents(".um-field-group"),a=t.data("max_entries");return 1<t.find(".um-field-group-body").length?jQuery(this).parents(".um-field-group-body").remove():jQuery(this).parents(".um-field-group-body").hide(),0<a&&t.find(".um-field-group-body").length<a&&t.find(".um-field-group-head").removeClass("disabled"),!1}),jQuery(document.body).on("click",".um-ajax-paginate",function(e){e.preventDefault();var t=jQuery(this),a=jQuery(this).parent();a.addClass("loading");var i=jQuery(this).data("hook");if("um_load_posts"===i){var r=1*jQuery(this).data("pages"),n=1*jQuery(this).data("page")+1;jQuery.ajax({url:wp.ajax.settings.url,type:"post",data:{action:"um_ajax_paginate_posts",author:jQuery(this).data("author"),page:n,nonce:um_scripts.nonce},complete:function(){a.removeClass("loading")},success:function(e){a.before(e),n===r?a.remove():t.data("page",n)}})}else{var u=jQuery(this).data("args"),o=jQuery(this).parents(".um").find(".um-ajax-items");jQuery.ajax({url:wp.ajax.settings.url,type:"post",data:{action:"um_ajax_paginate",hook:i,args:u,nonce:um_scripts.nonce},complete:function(){a.removeClass("loading")},success:function(e){a.remove(),o.append(e)}})}}),jQuery(document).on("click",".um-ajax-action",function(e){e.preventDefault();var t=jQuery(this).data("hook"),a=jQuery(this).data("user_id"),arguments=jQuery(this).data("arguments");return jQuery(this).data("js-remove")&&jQuery(this).parents("."+jQuery(this).data("js-remove")).fadeOut("fast"),jQuery.ajax({url:wp.ajax.settings.url,type:"post",data:{action:"um_muted_action",hook:t,user_id:a,arguments:arguments,nonce:um_scripts.nonce},success:function(e){}}),!1}),jQuery(document).on("click","#um-search-button",function(){jQuery(this).parents("form").submit()}),jQuery(".um-form input[class=um-button][type=submit]").removeAttr("disabled"),jQuery(document).one("click",".um:not(.um-account) .um-form input[class=um-button][type=submit]:not(.um-has-recaptcha)",function(){jQuery(this).attr("disabled","disabled"),jQuery(this).parents("form").submit()});var o={};function s(e,t,a,i){var r=e.data("um-parent");e.attr("name"),jQuery('select[name="'+r+'"]');e.find('option[value!=""]').remove(),e.hasClass("um-child-option-disabled")||e.removeAttr("disabled");i=[];var n="";if("yes"===t.post.members_directory){var u=new URLSearchParams(window.location.search);n=u.get(t.post.child_name)}jQuery.each(t.items,function(e,t){i.push({id:e,text:t,selected:t===n})}),e.select2("destroy"),e.select2({data:i,allowClear:!0,minimumResultsForSearch:10}),"yes"!==t.post.members_directory&&(void 0===t.field.default||e.data("um-original-value")?""!=e.data("um-original-value")&&e.val(e.data("um-original-value")).trigger("change"):e.val(t.field.default).trigger("change"),0==t.field.editable&&(e.addClass("um-child-option-disabled"),e.attr("disabled","disabled"))),o[a]=t}jQuery("select[data-um-parent]").each(function(){var r=jQuery(this),n=r.data("um-parent"),u=(r.data("um-ajax-url"),r.data("um-ajax-source"));r.val();r.attr("data-um-init-field",!0),jQuery(document).on("change",'select[name="'+n+'"]',function(){var t=jQuery(this),e=t.closest("form").find("input[type=hidden][name=form_id]").val(),a=t.val();if(""!=t.val()&&"object"!=typeof o[a]&&jQuery.ajax({url:wp.ajax.settings.url,type:"post",data:{action:"um_select_options",parent_option_name:n,parent_option:t.val(),child_callback:u,child_name:r.attr("name"),members_directory:r.attr("data-mebers-directory"),form_id:e,nonce:um_scripts.nonce},success:function(e){"success"==e.status&&""!=t.val()&&s(r,e,a),void 0!==e.debug&&console.log(e)},error:function(e){console.log(e)}}),""!=t.val()&&"object"==typeof o[a]){var i=o[a];s(r,i,a)}""==t.val()&&(r.find('option[value!=""]').remove(),r.val("").trigger("change"))}),jQuery('select[name="'+n+'"]').trigger("change")})});