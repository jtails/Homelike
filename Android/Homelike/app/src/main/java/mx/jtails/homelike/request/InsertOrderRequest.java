package mx.jtails.homelike.request;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mx.jtails.homelike.api.endpoint.pedidoendpoint.Pedidoendpoint;
import mx.jtails.homelike.api.model.CantidadPago;
import mx.jtails.homelike.api.model.Cuenta;
import mx.jtails.homelike.api.model.DetallePedido;
import mx.jtails.homelike.api.model.Pedido;
import mx.jtails.homelike.api.model.Producto;
import mx.jtails.homelike.api.model.Proveedor;
import mx.jtails.homelike.model.provider.HomelikeDBManager;

/**
 * Created by GrzegorzFeathers on 9/16/14.
 */
public class InsertOrderRequest extends HomelikeApiRequest {

    private Pedido mOrder;
    private int mAccountId;
    private Context mContext;

    public InsertOrderRequest(HomelikeResponseHandler handler, Context context,
        int accountId, Map<Producto, Integer> rawOrder, Proveedor provider,
        CantidadPago paymentQuantity, int addressId, String comments) {
        super(handler);
        this.mEndpoint = new Pedidoendpoint.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, null).build();

        this.mContext = context;
        this.mAccountId = accountId;

        this.mOrder = new Pedido();
        this.mOrder.setCantidadPago(paymentQuantity);
        this.mOrder.setDetallePedido(this.generateOrderDetail(rawOrder));
        this.mOrder.setDireccion(HomelikeDBManager.getDBManager().getAddress(addressId));
        this.mOrder.setProveedor(provider);
        this.mOrder.setComentarioCliente(comments);
    }

    private List<DetallePedido> generateOrderDetail(Map<Producto, Integer> rawOrder){
        List<DetallePedido> orderDetails = new ArrayList<DetallePedido>();

        for(Producto p : rawOrder.keySet()){
            DetallePedido orderDetail = new DetallePedido();
            orderDetail.setProducto(p);
            orderDetail.setCantidad(rawOrder.get(p));
            orderDetails.add(orderDetail);
        }

        return orderDetails;
    }

    @Override
    protected Object doRequest() throws Exception {
        Object objCuenta = new GetAccountRequest(null, this.mAccountId).executeOnThread();
        if(objCuenta == null){
            return null;
        }

        Cuenta account = (Cuenta) objCuenta;
        this.mOrder.setCuenta(account);
        this.mOrder.setDispositivo(account.getDispositivos().get(0));
        return ((Pedidoendpoint) this.mEndpoint).insertPedido(this.mOrder).execute();
    }

    @Override
    protected void notifyResponseHandler(Object o) {
        if(this.mReponseHandler != null){
            ((OnInsertOrderResponseHandler) this.mReponseHandler).onInsertOrderResponse(
                    o == null ? null : (Pedido) o);
        }
    }

    public interface OnInsertOrderResponseHandler extends HomelikeResponseHandler {
        public void onInsertOrderResponse(Pedido order);
    }
}
