var working = false;
function runAuthenticating() {
	 $("#btn").show();
	 $('#btnLogin').hide();

  if (working) return;
  working = true;
  var $this = $('.login'),
  $state = $this.find('button > .state');
  //$state = $('button');
  //$state = $this.find('.state');
  $this.addClass('loading');
  //$('button').val('Authenticating');
  $state.html('Authenticating');
  setTimeout(function() {
    $this.addClass('ok');
    $state.html('Welcome back!');
    setTimeout(function() {
      $state.html('Log inaaa');
      $this.removeClass('ok loading');
      working = false;
    }, 1000);
  }, 10000);
  return true;
};

