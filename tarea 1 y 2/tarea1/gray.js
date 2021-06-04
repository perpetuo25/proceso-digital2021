var cnv = document.getElementById('output');
var cnx = cnv.getContext('2d');

function grey(input) {
    cnx.drawImage(myimage, 0 , 0);
    var width = input.width;
    var height = input.height;
    var imgPixels = cnx.getImageData(0, 0, width, height);

    for(var y = 0; y < height; y++){
        for(var x = 0; x < width; x++){
            var i = (y * 4) * width + x * 4;
            var avg = (imgPixels.data[i] + imgPixels.data[i + 1] + imgPixels.data[i + 2]) / 3;
            imgPixels.data[i] = avg;
            imgPixels.data[i + 1] = avg;
            imgPixels.data[i + 2] = avg;
        }
    }

    cnx.putImageData(imgPixels, 0, 0, 0, 0, imgPixels.width, imgPixels.height);
}

function grey2(input) {
    cnx.drawImage(myimage, 0 , 0);
    var width = input.width;
    var height = input.height;
    var imgPixels = cnx.getImageData(0, 0, width, height);

    for(var y = 0; y < height; y++){
        for(var x = 0; x < width; x++){
            var i = (y * 4) * width + x * 4;
            var avg = (imgPixels.data[i]*0.3 + imgPixels.data[i + 1] *0.59+ imgPixels.data[i + 2]*0.11);
            imgPixels.data[i] = avg;
            imgPixels.data[i + 1] = avg;
            imgPixels.data[i + 2] = avg;
        }
    }

    cnx.putImageData(imgPixels, 0, 0, 0, 0, imgPixels.width, imgPixels.height);
}

function grey3(input) {
    cnx.drawImage(myimage, 0 , 0);
    var width = input.width;
    var height = input.height;
    var imgPixels = cnx.getImageData(0, 0, width, height);

    for(var y = 0; y < height; y++){
        for(var x = 0; x < width; x++){
            var i = (y * 4) * width + x * 4;
            var avg = (imgPixels.data[i]*0.2126 + imgPixels.data[i + 1] *0.7152+ imgPixels.data[i + 2]*0.0722);
            imgPixels.data[i] = avg;
            imgPixels.data[i + 1] = avg;
            imgPixels.data[i + 2] = avg;
        }
    }

    cnx.putImageData(imgPixels, 0, 0, 0, 0, imgPixels.width, imgPixels.height);
}


function grey4(input) {
    cnx.drawImage(myimage, 0 , 0);
    var width = input.width;
    var height = input.height;
    var imgPixels = cnx.getImageData(0, 0, width, height);

    for(var y = 0; y < height; y++){
        for(var x = 0; x < width; x++){
            var i = (y * 4) * width + x * 4;
            var avg = (Math.max(imgPixels.data[i], imgPixels.data[i + 1] , imgPixels.data[i + 2]) + Math.min(imgPixels.data[i], imgPixels.data[i + 1] , imgPixels.data[i + 2]))/2 ;
            imgPixels.data[i] = avg;
            imgPixels.data[i + 1] = avg;
            imgPixels.data[i + 2] = avg;
        }
    }

    cnx.putImageData(imgPixels, 0, 0, 0, 0, imgPixels.width, imgPixels.height);
}
function grey5(input) {
    cnx.drawImage(myimage, 0 , 0);
    var width = input.width;
    var height = input.height;
    var imgPixels = cnx.getImageData(0, 0, width, height);

    for(var y = 0; y < height; y++){
        for(var x = 0; x < width; x++){
            var i = (y * 4) * width + x * 4;
            var avg = Math.max(imgPixels.data[i], imgPixels.data[i + 1] , imgPixels.data[i + 2]) ;
            imgPixels.data[i] = avg;
            imgPixels.data[i + 1] = avg;
            imgPixels.data[i + 2] = avg;
        }
    }

    cnx.putImageData(imgPixels, 0, 0, 0, 0, imgPixels.width, imgPixels.height);
}
function grey6(input) {
    cnx.drawImage(myimage, 0 , 0);
    var width = input.width;
    var height = input.height;
    var imgPixels = cnx.getImageData(0, 0, width, height);

    for(var y = 0; y < height; y++){
        for(var x = 0; x < width; x++){
            var i = (y * 4) * width + x * 4;
            var avg = Math.min(imgPixels.data[i], imgPixels.data[i + 1] , imgPixels.data[i + 2]) ;
            imgPixels.data[i] = avg;
            imgPixels.data[i + 1] = avg;
            imgPixels.data[i + 2] = avg;
        }
    }

    cnx.putImageData(imgPixels, 0, 0, 0, 0, imgPixels.width, imgPixels.height);
}

function grey7(input) {
    cnx.drawImage(myimage, 0 , 0);
    var width = input.width;
    var height = input.height;
    var imgPixels = cnx.getImageData(0, 0, width, height);

    for(var y = 0; y < height; y++){
        for(var x = 0; x < width; x++){
            var i = (y * 4) * width + x * 4;
            var avg = imgPixels.data[i] ;
            imgPixels.data[i] = avg;
            imgPixels.data[i + 1] = avg;
            imgPixels.data[i + 2] = avg;
        }
    }

    cnx.putImageData(imgPixels, 0, 0, 0, 0, imgPixels.width, imgPixels.height);
}
function grey8(input) {
    cnx.drawImage(myimage, 0 , 0);
    var width = input.width;
    var height = input.height;
    var imgPixels = cnx.getImageData(0, 0, width, height);

    for(var y = 0; y < height; y++){
        for(var x = 0; x < width; x++){
            var i = (y * 4) * width + x * 4;
            var avg =  imgPixels.data[i + 1]  ;
            imgPixels.data[i] = avg;
            imgPixels.data[i + 1] = avg;
            imgPixels.data[i + 2] = avg;
        }
    }

    cnx.putImageData(imgPixels, 0, 0, 0, 0, imgPixels.width, imgPixels.height);
}
function grey9(input) {
    cnx.drawImage(myimage, 0 , 0);
    var width = input.width;
    var height = input.height;
    var imgPixels = cnx.getImageData(0, 0, width, height);

    for(var y = 0; y < height; y++){
        for(var x = 0; x < width; x++){
            var i = (y * 4) * width + x * 4;
            var avg = imgPixels.data[i + 2] ;
            imgPixels.data[i] = avg;
            imgPixels.data[i + 1] = avg;
            imgPixels.data[i + 2] = avg;
        }
    }

    cnx.putImageData(imgPixels, 0, 0, 0, 0, imgPixels.width, imgPixels.height);
}

function grey10(input,brig) {
    var b = brig;
    if (brig > 255){
     b = 255;
    }
    if(brig<0){
       b = 0;
    }

    cnx.drawImage(myimage, 0 , 0);
    var width = input.width;
    var height = input.height;
    var imgPixels = cnx.getImageData(0, 0, width, height);

    for(var y = 0; y < height; y++){
        for(var x = 0; x < width; x++){
            var i = (y * 4) * width + x * 4;
            imgPixels.data[i] =imgPixels.data[i] + b ;
            imgPixels.data[i + 1] = imgPixels.data[i + 1] + b;
            imgPixels.data[i + 2] = imgPixels.data[i + 2] + b;
        }
    }

    cnx.putImageData(imgPixels, 0, 0, 0, 0, imgPixels.width, imgPixels.height);
}


function heightContrast(input){
  cnx.drawImage(myimage, 0 , 0);
  var width = input.width;
  var height = input.height;
  var imgPixels = cnx.getImageData(0, 0, width, height);

  for(var y = 0; y < height; y++){
      for(var x = 0; x < width; x++){
          var i = (y * 4) * width + x * 4;
          var avg = (imgPixels.data[i] + imgPixels.data[i + 1] + imgPixels.data[i + 2]) / 3;
          if(avg>127){
          imgPixels.data[i] = 255;
          imgPixels.data[i + 1] = 255;
          imgPixels.data[i + 2] = 255;
        }else{
          imgPixels.data[i] = 0;
          imgPixels.data[i + 1] = 0;
          imgPixels.data[i + 2] = 0;
        }
      }
  }

  cnx.putImageData(imgPixels, 0, 0, 0, 0, imgPixels.width, imgPixels.height);
}

function inverse(input){
  cnx.drawImage(myimage, 0 , 0);
  var width = input.width;
  var height = input.height;
  var imgPixels = cnx.getImageData(0, 0, width, height);

  for(var y = 0; y < height; y++){
      for(var x = 0; x < width; x++){
          var i = (y * 4) * width + x * 4;
          var avg = (imgPixels.data[i] + imgPixels.data[i + 1] + imgPixels.data[i + 2]) / 3;
          if(avg>127){
          imgPixels.data[i] = 0;
          imgPixels.data[i + 1] = 0;
          imgPixels.data[i + 2] = 0;
        }else{
          imgPixels.data[i] = 255;
          imgPixels.data[i + 1] = 255;
          imgPixels.data[i + 2] = 255;
        }
      }
  }

  cnx.putImageData(imgPixels, 0, 0, 0, 0, imgPixels.width, imgPixels.height);
}

function rgb(input) {
    cnx.drawImage(myimage, 0 , 0);
    var width = input.width;
    var height = input.height;
    var imgPixels = cnx.getImageData(0, 0, width, height);

    for(var y = 0; y < height; y++){
        for(var x = 0; x < width; x++){
            var i = (y * 4) * width + x * 4;

            imgPixels.data[i] = 200;
            imgPixels.data[i + 1] = 200;
            imgPixels.data[i + 2] = 100;
            imgPixels.data[i+3] = 0.3;
        }
    }

    cnx.putImageData(imgPixels, 0, 0, 0, 0, imgPixels.width, imgPixels.height);
}



var myimage = new Image();
myimage.crossOrigin="anonymous";
myimage.src = "https://tannerhelland.com/images/Secret_of_Monkey_Island-600x375.jpg";
