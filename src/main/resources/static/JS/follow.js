function toggleFollow() {
    const button = document.getElementById("followButton");
    const usernameHolder = document.getElementById("usernameHolder");
    const username = usernameHolder.dataset.username;

    if (button.textContent === "Follow") {
        alert("Enjoy Parking Together!!" /*+ username + "."*/);
        button.textContent = "Unfollow";
    } else {
        alert("It's not you...Plenty of other Parkers to meet! "/* + username + "."*/);
        button.textContent = "Follow";
    }
}