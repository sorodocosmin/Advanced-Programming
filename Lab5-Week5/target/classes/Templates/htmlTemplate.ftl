<html>
<head>
    <title>Catalog</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<p><strong>Catalog : ${catalog.getName()}</strong></p>

<ol>
    <#list catalog.getDocuments() as document>
        <li ><p id="title_docs"> Name : <u>${document.getName()}</u> </p>
            <ul>
                <li> ID : ${document.getId()}</li>
                <li> Location : <a href="${document.getPath()}">Click Here</a> </li>
                <li> Tags :
                    <#assign nrTags = document.getTags()?size>
                    <#if nrTags == 0>
                        This document has no tags
                    <#else>
                        <ul>
                        <#list document.getTags()?keys as key>
                            <li>${key} = ${document.getTags()[key]};</li>
                        </#list>
                        </ul>
                    </#if>
                </li>
            </ul>
        </li>
    </#list>
</ol>
</body>
</html>