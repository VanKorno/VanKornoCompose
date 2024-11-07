package com.vankorno.vankornocompose.actions

import com.vankorno.vankornocompose.R

class MiscUI {
    
    fun getCircleIcon(isON: Boolean) =  if (isON)
                                            R.drawable.ic_check_circle_filled_24
                                        else
                                            R.drawable.ic_check_circle_empty_24
    
    fun getCheckBoxIcon(isON: Boolean) =  if (isON)
                                            R.drawable.ic_check_box_filled_24
                                        else
                                            R.drawable.ic_check_box_empty_24
    
    
    
    
}