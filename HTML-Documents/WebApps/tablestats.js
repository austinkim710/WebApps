var table = document.getElementById("Stats");
var rows = table.getElementsByTagName("TR");

function sortByName(n) {
  var i,x,y, swap;
  var switching = true;
  while (switching) {
    switching = false;
    for (i=1; i < (rows.length -1); i++) {
      swap = false;
      x = rows[i].getElementsByTagName("TD") [n];
      y = rows[i+1].getElementsByTagName("TD") [n];
      if (x.innerHTML > y.innerHTML) {
        swap = true;
        break;
      }
    }

    if(swap) {
      var tmp = rows[i].innerHTML;
      rows[i].innerHTML = rows[i+1].innerHTML;
      rows[i+1].innerHTML = tmp;
      switching = true;
    }
  }
}

function sortByScore(n) {
    var i,x, y, swap;
    var switching = true;
    while (switching) {
        switching = false;
        for (i = 1; i < rows.length - 1; i++) {
            swap = false;
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            if (parseFloat(x.innerHTML) < parseFloat(y.innerHTML)) {
                swap = true;
                break;
            }
        }

    if (swap) {
        var tmp = rows[i].innerHTML;
        rows[i].innerHTML = rows[i + 1].innerHTML
        rows[i + 1].innerHTML = tmp;
        switching = true;
    }
  }
}

function search() {
  var input, filter, table, tr, td, i;
  input = document.getElementById("SearchBar");
  filter = input.value.toUpperCase();
  table = document.getElementById("Stats");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}
