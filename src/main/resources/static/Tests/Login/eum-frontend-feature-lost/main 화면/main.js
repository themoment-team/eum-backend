function scrollLeft() {
  document.getElementById('cardWrapper').scrollBy({
    left: -300,
    behavior: 'smooth',
  });
}

function scrollRight() {
  document.getElementById('cardWrapper').scrollBy({
    left: 300,
    behavior: 'smooth',
  });
}
