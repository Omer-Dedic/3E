package com.example.a3e

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomExpandableListAdapter(
    private val context: Context,
    private val groupList: List<String>,
    private val childMap: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int = groupList.size

    override fun getChildrenCount(groupPosition: Int): Int =
        childMap[groupList[groupPosition]]?.size ?: 0

    override fun getGroup(groupPosition: Int): Any = groupList[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int): Any =
        childMap[groupList[groupPosition]]?.get(childPosition) ?: ""

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    override fun hasStableIds(): Boolean = false

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val groupTitle = getGroup(groupPosition) as String
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.group_item, parent, false)

        val groupTextView = view.findViewById<TextView>(R.id.groupTextView)
        val groupIcon = view.findViewById<ImageView>(R.id.groupIcon)

        // Postavljanje naslova grupe
        groupTextView.text = groupTitle

        // Promjena ikone na osnovu stanja (otvoreno/zatvoreno)
        groupIcon.setImageResource(
            if (isExpanded) R.drawable.icons8dropdownflipped else R.drawable.ic_dropdown_arrow
        )

        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val childTitle = getChild(groupPosition, childPosition) as String
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.dropdown_item, parent, false)

        val childTextView = view.findViewById<TextView>(R.id.textView7)
        childTextView.text = childTitle

        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true
}