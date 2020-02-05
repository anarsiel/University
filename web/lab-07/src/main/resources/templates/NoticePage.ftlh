<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="enter form-box">
        <div class="header">Enter</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="createNotice"/>
                <div class="field">
                    <div class="name">
                        <label for="content"> Text </label>
                    </div>
                    <div class="value">
                        <textarea id="content" name="content"></textarea>
                    </div>
                    <@c.error "noticeForm.content"/>
                </div>
                <div class="button-field">
                    <input type="submit" value="Enter">
                </div>
            </form>
        </div>
    </div>
</@c.page>
