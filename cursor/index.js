function createLeakyHandler() {
  const hugeObject = new Array(1000000).fill('leak'); // chiếm bộ nhớ lớn
  document.body.addEventListener('click', function handleClick() {
      console.log('Clicked!');
  });
}

createLeakyHandler();