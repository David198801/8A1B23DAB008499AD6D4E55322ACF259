不符合条件的遍历项会变成注释

```javascript
<ul class="clearfix" v-for="category in categories" v-if="category.categoryId==current">
   <li v-for="categoryChild in category.categoryChildren"><a href="#">{{categoryChild.name}}</a></li>
</ul>
```

