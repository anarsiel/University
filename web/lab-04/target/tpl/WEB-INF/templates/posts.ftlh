<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "common.ftl" as c/>

<@c.page>
<#list posts?reverse as post>
    <#if post.user_id==user_id>
        <@c.makePost post=post zip=true/>
    </#if>
</#list>
</@c.page>