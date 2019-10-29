<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "common.ftl" as c/>

<@c.page>

    <#if handle??>
        <#assign viewedUser=c.findBy(users, "handle", handle)!/>
    </#if>
    <#if user_id??>
        <#assign viewedUser=c.findBy(users, "id", user_id)!/>
    </#if>
    <#if viewedUser.id??>
        <div class="userpage">
            <div class="userinfo">
                <@c.userlink user=viewedUser nameOnly=false/>
                <ul>
                    <li>id - ${viewedUser.id}</li>
                    <li>name - ${viewedUser.name}</li>
                    <li>handle - ${viewedUser.handle}</li>
                    <li><a href="/posts?user_id=${viewedUser.id}">count of posts - ${c.postsCount(viewedUser)}</a></li>
                </ul>
            </div>
            <img src="/img/user.jpg">
        </div>
        <@c.linkArrow currentUser=viewedUser userlist=users?reverse code="&#8592;"/>
        <@c.linkArrow currentUser=viewedUser userlist=users code="&#8594;"/>
    <#else>
        <h1>No such user</h1>
    </#if>
</@c.page>
