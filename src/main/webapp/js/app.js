//var rootURL = "http://rest.gpxdb.com/gpxrs/api/v1";
var rootURL = "http://localhost:8080/gpxrs/api/v1";

var dataObject;

var GpxType = function(gpxObj) {
	this.metadata = gpxObj.metadata;
	this.wpt = gpxObj.wpt;
	this.rte = gpxObj.rte;
	this.trk = gpxObj.trk;
};
var Metadata;

Metadata.prototype.work = function() {
	return this.name + ' is working.';
};



var Metadata = Backbone.Model.extend ({
	defaults: {
		name: 'John Doe',
		description: '',
		keywords: 'one, two, three'
	},
	work: function(){
		return this.get('name') + ' is working.';
	}
});