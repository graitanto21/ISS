jQuery(document).ready(function(){jQuery(".um-members").each(function(){UM_Member_Grid(jQuery(this))}),jQuery(".um-directory .um-member-connect").each(function(){0==jQuery(this).find("a").length&&jQuery(this).remove()}),jQuery(".um-member-meta-main").each(function(){0==jQuery(this).find(".um-member-metaline").length&&0==jQuery(this).find(".um-member-connect").find("a").length&&jQuery(this).remove()}),jQuery(document.body).on("click",".um-member-more a",function(e){e.preventDefault();var r=jQuery(this).parents(".um-member"),m=jQuery(this).parents(".um-members");return r.find(".um-member-more").hide(),r.find(".um-member-meta").slideDown(function(){UM_Member_Grid(m)}),r.find(".um-member-less").fadeIn(),setTimeout(function(){UM_Member_Grid(m)},100),!1}),jQuery(document.body).on("click",".um-member-less a",function(e){e.preventDefault();var r=jQuery(this).parents(".um-member"),m=jQuery(this).parents(".um-members");return r.find(".um-member-less").hide(),r.find(".um-member-meta").slideUp(function(){r.find(".um-member-more").fadeIn(),UM_Member_Grid(m)}),!1}),jQuery(document.body).on("click",".um-do-search",function(e){return e.preventDefault(),jQuery(this).parents("form").find("input").filter(function(e){if(0===this.value.length)return!0}).prop("disabled",!0),jQuery(this).parents("form").find("select").filter(function(e){if(0===this.value.length)return!0}).prop("disabled",!0),jQuery(this).parents("form").submit(),!1})});