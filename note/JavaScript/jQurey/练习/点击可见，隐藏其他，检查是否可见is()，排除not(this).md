.is(':hidden')

```javascript
$(function(){
	$('.first').click(function(){
		if($(this).next().is(':hidden')){
			$('.second').hide()
			$(this).next().show()
		}else{
			$(this).next().hide()
		}
	})
})
```

或者

```javascript
$(function(){
	$('.first').click(function(){
		$(this).next().toggle();
		$('.second').not($(this).next()[0]).hide();
	})
})
```

