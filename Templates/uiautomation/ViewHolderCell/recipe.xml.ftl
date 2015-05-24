<?xml version="1.0"?>
<recipe>

    <instantiate from="res/layout/recycler_cell_simple.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />

    <instantiate from="src/app_package/SimpleViewHolder.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${viewHolderClass}.java" />

    <open file="${escapeXmlAttribute(srcOut)}/${viewHolderClass}.java" />
    <open file="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />
</recipe>
