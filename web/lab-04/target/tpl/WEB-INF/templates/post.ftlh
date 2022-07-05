<#-- @ftlvariable name="post_id" type="java.lang.Long" -->
<#import "common.ftl" as c/>

<@c.page>
    <#assign viewedPost=c.findBy(posts, "id", post_id)!/>
    <@c.makePost post=viewedPost zip=false/>
</@c.page>