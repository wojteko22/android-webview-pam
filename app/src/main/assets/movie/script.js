window.addEventListener('load', start)

function start () {
  var video = document.getElementById('video')
  var blueBtn = document.getElementById('blueBtn')
  var redBtn = document.getElementById('redBtn')
  blueBtn.addEventListener('click', function () { video.play() })
  redBtn.addEventListener('click', function () { video.pause() })
}
