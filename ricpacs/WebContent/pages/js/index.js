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
    $state.html('Oops! System is busy.');
    setTimeout(function() {
      $state.html('Log in');
      $this.removeClass('ok loading');
      working = false;
    }, 1000);
  }, 20000);
  return true;
};

