App = {Context: ''};
App.Context = {
  getValue: function(key) {
      return this[key];
  },
  setValue: function(key, value) {
      this[key] = value;
  },
  getLastIndex: function() {
    if (!this["lastIndex"]) {
        return -1;
    }

    return this["lastIndex"];
  },
  setLastIndex: function(lastIndex) {
    this["lastIndex"] = lastIndex;
  } 
}

App.Context.setValue(1, 0);
App.Context.setValue("contacts", [
            {username: "barshachess25@gmail.com", avatarUrl: "https://b.zmtcdn.com/data/user_profile_pictures/60f/f377c66cfaa1c638256f2f2f8486e60f.jpg"},
            {username: "pratik006@gmail.com", avatarUrl: "https://media.licdn.com/mpr/mpr/shrink_100_100/AAEAAQAAAAAAAAdLAAAAJGU5ZWE2MjBjLTVlNDYtNDgxYi04OGVkLTY0Njg2YjMxYzA2Yw.jpg"}
        ]);

function render(tmpl_name, tmpl_data) {
    if ( !render.tmpl_cache ) { 
        render.tmpl_cache = {};
    }

    if ( ! render.tmpl_cache[tmpl_name] ) {
        var tmpl_dir = 'js/templates';
        var tmpl_url = tmpl_dir + '/' + tmpl_name + '.html';
        var tmpl_string;
        $.ajax({
            url: tmpl_url,
            method: 'GET',
            async: false,
            success: function(data) {
                tmpl_string = data;
            }
        });

        render.tmpl_cache[tmpl_name] = _.template(tmpl_string);
    }
    return render.tmpl_cache[tmpl_name](tmpl_data);
}



/**
 * @author Alexander Manzyuk <admsev@gmail.com>
 * Copyright (c) 2012 Alexander Manzyuk - released under MIT License
 * https://github.com/admsev/jquery-play-sound
 * Usage: $.playSound('http://example.org/sound');
**/

(function($){

    $.fn.serializeObject = function()
    {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    }

  $.extend({
    playSound: function(){
      return $(
        '<audio autoplay="autoplay" style="display:none;" preload="auto">'
          + '<source src="' + arguments[0] + '.mp3" />'
          + '<source src="' + arguments[0] + '.ogg" />'
          + '<embed src="' + arguments[0] + '.mp3" hidden="true" autostart="true" loop="false" class="playSound" />'
        + '</audio>'
      ).appendTo('body');
    }
  });

})(jQuery);