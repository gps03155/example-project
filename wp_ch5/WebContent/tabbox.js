var tabBox = {
			__liSelected: null, // 외부 접근하지 말라 : private
			
			init: function(){
				var li = document.getElementById("tab1");
				li.onclick = this.__onClicked;
				
				li = document.getElementById("tab2");
				li.onclick = this.__onClicked;
				
				li = document.getElementById("tab3");
				li.onclick = this.__onClicked;
				
				li = document.getElementById("tab4");
				li.onclick = this.__onClicked;
				
				li = document.getElementById("tab5");
				li.onclick = this.__onClicked;
			},
			
			__onClicked: function(){
				console.log("clicked " + this.innerText);
				
				// this : onClick을 받은 element
				if(tabBox.__liSelected != null){
					// unselect 
					tabBox.__liSelected.className = "";
				}
				
				// select 
				this.className = "selected";
				tabBox.__liSelected = this;
			}
	}
	
	window.onload = function(){
		tabBox.init();
	}