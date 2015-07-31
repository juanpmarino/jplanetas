/** google global namespace for Google projects. */
var google = google || {};

/** appengine namespace for Google Developer Relations projects. */
google.appengine = google.appengine || {};

/** samples namespace for App Engine sample code. */
google.appengine.samples = google.appengine.samples || {};

/** hello namespace for this sample. */
com.jpmarino.jplanetas.clima = com.jpmarino.jplanetas.clima || {};

/**
 * Initializes the application.
 * @param {string} apiRoot Root of the API's path.
 */
com.jpmarino.jplanetas.clima.init = function(apiRoot) {
  // Loads the OAuth and helloworld APIs asynchronously, and triggers login
  // when they have completed.
  var apisToLoad;
  var callback = function() {
    if (--apisToLoad == 0) {
      com.jpmarino.jplanetas.clima.enableButtons();
    }
  }

  apisToLoad = 1; // must match number of calls to gapi.client.load()
  gapi.client.load('clima', 'v1', callback, apiRoot);
};

com.jpmarino.jplanetas.clima.enableButtons = function() {
	  var obtenerClima = document.querySelector('#obtenerClima');
	  obtenerClima.addEventListener('click', function(e) {
	    com.jpmarino.jplanetas.clima.obtenerClima(
	        document.querySelector('#dia').value);
	  });
};

com.jpmarino.jplanetas.clima.getGreeting = function(id) {
	  gapi.client.helloworld.greetings.getGreeting({'id': id}).execute(
	      function(resp) {
	        if (!resp.code) {
	          com.jpmarino.jplanetas.clima.print(resp);
	        }
	      });
	};

