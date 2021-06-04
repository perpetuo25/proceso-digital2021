// FILTERS
var filters = {
  // IDENTITY
  identity: [ [ 1 ] ],


}

function drawImage( filter, cb )
{
  canvas.width = img.width
  canvas.height = img.height
  context.drawImage( img, 0, 0 )

  var imageData = context.getImageData( 0, 0, img.width, img.height )

  setTimeout( function() {
    var start = new Date
    imageData = applyFilter( imageData, filter )
    console.log("Funciona", ((new Date) - start ) / 1000 + "s" )
    context.putImageData( imageData, 0, 0 )
    cb()
  })
}

function setFilter( filter )
{
  byId( "filters").setAttribute( "disabled", "1" )
  byId( "apply").setAttribute( "disabled", "1" )

  drawImage( filter, function() {
    byId( "filters").removeAttribute( "disabled" )
    byId( "apply").removeAttribute( "disabled" )
  })
}

function setPresetFilter( filterName )
{
  var filter = filters[ filterName ]
  setFilter( filter )

  var i0 = ( 5 - filter.length ) / 2

  for ( var i = 0; i < 5; i++ )
    for ( var j = 0; j < 5; j++ ) {
      var v = 0
      if ( filter[ i - i0 ] && filter[ i - i0 ][ j - i0 ] )
        v = filter[ i - i0 ][ j - i0 ]
      byId( "a" + i + j ).value = v
    }
}

function setCustomFilter()
{
  var filter = []

  for ( var i = 0; i < 5; i++ )
    for ( var j = 0; j < 5; j++ ) {
      filter[i] = filter[i] || []
      filter[i][j] = +byId( "a" + i + j ).value
    }

  setFilter( filter )
}

function reset()
{
  setPresetFilter( byId( "filters").value )
}

var byId = function( id ) { return document.getElementById( id ) }
var canvas = byId("canvas")
var context = canvas.getContext("2d")

var img = document.createElement("img")
img.crossOrigin="anonymous";
img.onload = function() { setPresetFilter( "identity" ) }
img.src = "https://tannerhelland.com/images/Secret_of_Monkey_Island-600x375.jpg"
