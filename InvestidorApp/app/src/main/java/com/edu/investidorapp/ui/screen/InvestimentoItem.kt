package com.edu.investidorapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edu.investidorapp.model.Investimento

@Composable
fun InvestimentoItem (investimento : Investimento) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults .cardColors (containerColor = MaterialTheme .colorScheme .surface ),
        elevation = CardDefaults .cardElevation (8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                . fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Ícone de investimento" ,
                tint = MaterialTheme .colorScheme .primary ,
                modifier = Modifier .size(40.dp)
            )
            Spacer(modifier = Modifier .width(16.dp))
            Column(
                modifier = Modifier .weight(1f)
            ) {
                Text(
                    text = investimento .nome,
                    style = MaterialTheme .typography .titleMedium .copy( fontWeight = FontWeight .Bold),
                    color = MaterialTheme .colorScheme .onSurface
                )
                Text(
                    text = "Valor: R$ ${investimento .valor }",
                    style = MaterialTheme .typography .bodyMedium .copy( fontSize = 14.sp),
                    color = MaterialTheme .colorScheme .onSurface .copy( alpha = 0.7f)
                )
            }
        }
    }
}