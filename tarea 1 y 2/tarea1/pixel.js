var ctx = canvas.getContext('2d'),
    img = new Image(),
    play = false;
ctx.mozImageSmoothingEnabled = false;
ctx.webkitImageSmoothingEnabled = false;
ctx.imageSmoothingEnabled = false;
img.onload = pixelate;

img.src = 'https://tannerhelland.com/images/Secret_of_Monkey_Island-600x375.jpg';

// MAIN function
function pixelate(v) {
    var size = (play ? v : blocks.value) * 0.01,
        w = canvas.width * size,
        h = canvas.height * size;
    console.log(size)
    // draw original image to the scaled size
    ctx.drawImage(img, 0, 0, w, h);

    // then draw that scaled image thumb back to fill canvas
    // As smoothing is off the result will be pixelated
    ctx.drawImage(canvas, 0, 0, w, h, 0, 0, canvas.width, canvas.height);
}


// event listeneners for slider and button
blocks.addEventListener('change', pixelate, false);
animate.addEventListener('click', toggleAnim, false);

// poly-fill for requestAnmationFrame with fallback for older
// browsers which do not support rAF.
window.requestAnimationFrame = (function () {
    return window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || function (callback) {
        window.setTimeout(callback, 1000 / 60);
    };
})();