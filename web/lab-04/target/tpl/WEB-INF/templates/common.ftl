<#-- @ftlvariable name="uri" type="java.lang.String" -->
<#macro headerListUnderline ref text>
    <li>
        <#if ref==uri>
            <a href="${ref}" class="underline_ref">${text}</a>
        <#else>
            <a href="${ref}">${text}</a>
        </#if>
</#macro>

<#macro header>
<header>
    <a href="/"><img src="/img/logo.png" alt="Codeforces" title="Codeforces"/></a>
    <div class="languages">
        <a href="#"><img src="/img/gb.png" alt="In English" title="In English"/></a>
        <a href="#"><img src="/img/ru.png" alt="In Russian" title="In Russian"/></a>
    </div>
    <div class="enter-or-register-box">
        <#if user??>
            <@userlink user=user nameOnly=true/>
            |
            <a href="#">Logout</a>
        <#else>
            <a href="#">Enter</a>
            |
            <a href="#">Register</a>
        </#if>
    </div>
    <nav>
        <ul>
            <@headerListUnderline ref="/index" text="Index"/>
            <@headerListUnderline ref="/misc/help" text="Help"/>
            <@headerListUnderline ref="/users" text="Users"/>
        </ul>
    </nav>
</header>
</#macro>

<#macro sidebar>
<aside>
    <#list posts?reverse as post>
    <section>
        <div class="header">
            Post ${post.id}
        </div>
        <div class="body">
            ${post.text?replace("(\n)", "<br/>", "r")?truncate(250, "...")}
        </div>
        <div class="footer">
            <a href="/post?post_id=${post.id}">View all</a>
        </div>
    </section>
    </#list>
</aside>
</#macro>

<#macro footer>
<footer>
    <a href="#">Codeforces</a> &copy; 2010-2019 by Mike Mirzayanov
</footer>
</#macro>

<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Codeforces</title>
    <link rel="stylesheet" type="text/css" href="/css/normalize.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
</head>
<body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
</body>
</html>
</#macro>

<#macro makePost post zip>
    <#assign author=findBy(users, "id", post.user_id)/>
    <#assign text=post.text?replace("(\n)", "<br/>", "r")/>
    <#if zip>
        <#assign text=text?truncate(250, "...")/>
    </#if>
    <article class="post">
        <div class="title">${post.title}</div>
        <div class="information"><@userlink user=author nameOnly=false/></div>
        <div class="body">${text}</div>
        <div class="footer">
            <div class="left">
                <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">+173</span>
                <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                2 days ago
                <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="#">68</a>
            </div>
        </div>
    </article>
</#macro>

<#macro makeLinkByUser user text>
    <a href="/user?handle=${user.handle}">${text}</a>
</#macro>

<#macro userlink user nameOnly>
    <#if !nameOnly>
        <a href="/user?handle=${user.handle}"
           style="color: ${user.color.name()};
                   text-decoration: none;"
        >${user.handle}</a>
    <#else>
        <a href="/user?handle=${user.handle}">${user.handle}</a>
    </#if>
</#macro>

<#macro linkArrow currentUser userlist code>
    <#list userlist as user>
        <#if user==currentUser>
            <#if user_has_next>
                <@makeLinkByUser user=userlist[user_index+1] text=code/>
                <#else>
                    ${code}
            </#if>
        </#if>
    </#list>
</#macro>

<#function findBy items key id>
    <#list items as item>
        <#if item[key]==id>
            <#return item/>
        </#if>
    </#list>
</#function>

<#function postsCount user>
    <#assign count=0/>
    <#list posts as post>
        <#if post.user_id==user.id>
            <#assign count=count+1>
        </#if>
    </#list>
    <#return count>
</#function>
