function websiteVisits(response) {
    document.querySelector("#visits").textContent = response.value;
}

/*
var counterContainer = document.querySelector("#visits");
var resetButton = document.querySelector("#reset");
var visitCount = localStorage.getItem("websiteVisits");

function websiteVisits(response) {
    document.querySelector("#visits").textContent = response.value;
}


// Check if page_view entry is present
if (visitCount) {
  visitCount = Number(visitCount) + 1;
  localStorage.setItem("websiteVisits", visitCount);
} else {
  visitCount = 1;
  localStorage.setItem("websiteVisits", 1);
}
counterContainer.innerHTML = visitCount;

// Adding onClick event listener
resetButton.addEventListener("click", () => {
  visitCount = 1;
  localStorage.setItem("websiteVisits", 1);
  counterContainer.innerHTML = visitCount;
});
*/
