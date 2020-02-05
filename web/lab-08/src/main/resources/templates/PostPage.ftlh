<#-- @ftlvariable name="post" type="ru.itmo.wp.domain.Post" -->
<#-- @ftlvariable name="comment" type="ru.itmo.wp.domain.Comment" -->s
<#import "commons.ftlh" as c>

<@c.page>
    <@c.post post=post/>
    <#if user??>
        <div class="commentAdding form">
            <div class="body">
                <form method="post" action="">
                    <div class="value">
                        <textarea id="content" name="content">${comment.text!}</textarea>
                    </div>
                    <div class="button-field">
                        <input type="submit" value="Add comment">
                    </div>
                </form>
            </div>
        </div>
    </#if>

    <div class="comments">
        <#list post.comments as comment>
            <div class="information">By ${comment.user.login}</div>
            <div class="body">
                ${comment.text}
            </div>
        </#list>
    </div>
</@c.page>