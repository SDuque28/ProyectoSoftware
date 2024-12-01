import requests
import pandas as pd
import streamlit as st
from datetime import datetime 

with open('styles.css') as f:
    css = f.read()
    st.markdown(f'<style>{css}</style>', unsafe_allow_html=True)

st.title("Proyecto Factura Ya")

tab1, tab2, tab3 = st.tabs(["Clientes","Productos","Impuesto"])
api_clientes = "http://localhost:8080/clientes"
api_productos = "http://localhost:8080/productos"
api_impuestos = "http://localhost:8080/impuestos"

# -- DATOS CLIENTE -- 
try:
    response = requests.get(api_clientes + "/all")  # Cambia la URL según sea necesario
    response.raise_for_status()  # Lanza una excepción si la respuesta tiene un error HTTP
    clientes = response.json()  # Parsear la respuesta como JSON

    # Mostrar los datos en Streamlit
    if isinstance(clientes, list) and clientes:
        listadoClinetes = pd.DataFrame(clientes)
        listadoClinetes.columns = [col.capitalize() for col in listadoClinetes.columns]
        listadoClinetes.columns.values[1] = "Numero Documento"
    else:
        st.warning("No hay clientes para mostrar o la respuesta no es una lista.")
except requests.exceptions.RequestException as e:
        st.error(f"Error al conectar con la API: {e}")

# -- DATOS PRODUCTO -- 
try:
    response = requests.get(api_productos + "/all")  # Cambia la URL según sea necesario
    response.raise_for_status()  # Lanza una excepción si la respuesta tiene un error HTTP
    productos = response.json()  # Parsear la respuesta como JSON

    # Mostrar los datos en Streamlit
    if isinstance(productos, list) and productos:
        listadoProductos = pd.DataFrame(productos)
        listadoProductos.columns = [col.capitalize() for col in listadoProductos.columns]
        listadoProductos.columns.values[3] = "Precio Venta"
        listadoProductos.columns.values[4] = "Impuesto Id"
        listadoProductos.columns.values[6] = "Categoria Id"
    else:
        st.warning("No hay productos para mostrar o la respuesta no es una lista.")
except requests.exceptions.RequestException as e:
        st.error(f"Error al conectar con la API: {e}")

# -- DATOS PRODUCTO -- 
try:
    response = requests.get(api_impuestos + "/all")  # Cambia la URL según sea necesario
    response.raise_for_status()  # Lanza una excepción si la respuesta tiene un error HTTP
    impuestos = response.json()  # Parsear la respuesta como JSON

    # Mostrar los datos en Streamlit
    if isinstance(impuestos, list) and impuestos:
        listadoImpuestos = pd.DataFrame(impuestos)
        listadoImpuestos.columns = [col.capitalize() for col in listadoImpuestos.columns]
    else:
        st.warning("No hay impuestos para mostrar o la respuesta no es una lista.")
except requests.exceptions.RequestException as e:
        st.error(f"Error al conectar con la API: {e}")

with tab1:
    # --- CREAR CLIENTE ---
    st.subheader("Crear un Cliente:")

    col0,col1, col2, col3, col4, col5, col6, col7 = st.columns(8)
    # Columnas de los datos a actulizar
    with col0:
        idCliente = st.text_input("Id cliente")
    with col1:
        numDocumento = st.text_input("Numero Documento")
    with col2:
        nombre = st.text_input("Nombre")
    with col3:
        direccion = st.text_input("Direccion")
    with col4:
        telefono = st.text_input("Telefono")
    with col5:
        email = st.text_input("Email")
    with col6:
        ciudad = st.text_input("Ciudad")
    with col7:
        departamento = st.text_input("Departamento")

    if st.button("Crear Cliente"):
        # Validar campos requeridos
        if not idCliente or not numDocumento or not nombre or not direccion:
            st.error("Por favor, completa todos los campos requeridos.")
        else:
            # Estructura del cuerpo del POST
            payload = {
                "id": idCliente,
                "numeroDocumento": numDocumento,
                "nombre": nombre,
                "direccion": direccion,
                "telefono": telefono,
                "email": email,
                "ciudad": ciudad,
                "departamento": departamento
            }

            try:
                response = requests.post(api_clientes+"/add", json=payload)
                
                if response.status_code == 200:
                    st.success("Cliente agregado exitosamente.")
                else:
                    st.error(f"Error al agregar el cliente: {response.status_code} - {response.text}")
            except requests.exceptions.RequestException as e:
                st.error(f"Error de conexión: {e}")

    # --- MODIFICAR CLIENTE ---
    st.subheader("Modificar un Cliente:")

    # Inicializar estado persistente
    if "bandera" not in st.session_state:
        st.session_state.bandera = False
    if "indiceTabla" not in st.session_state:
        st.session_state.indiceTabla = -1
    if "count" not in st.session_state:
        st.session_state.count = False

    col1, col2, col3 = st.columns([1, 0.5, 6])

    with col1:
        p_id = st.text_input("Id del cliente a cambiar")
    with col2:
        st.markdown("<div style='margin-top: 1.6rem; text-align: center;'>", unsafe_allow_html=True)
        if st.button("Buscar"):
            st.session_state.count = True
            if p_id in listadoClinetes.iloc[:, 0].values:
                st.session_state.bandera = True
                st.session_state.indiceTabla = listadoClinetes[listadoClinetes.iloc[:, 0] == p_id].index[0]
            else:
                st.session_state.bandera = False

    with col3:
        if not st.session_state.bandera and st.session_state.count:
            st.markdown("<div style='margin-top: 1rem; text-align: center;'>", unsafe_allow_html=True)
            st.error("Cliente no encontrado")

    # Mostrar los campos solo si se encontró un cliente
    if st.session_state.bandera:
        st.divider()
        col0, col1, col2, col3, col4, col5, col6, col7= st.columns(8)
        with col0:
            idCliente = st.text_input("Id cliente", value=listadoClinetes.iloc[st.session_state.indiceTabla, 0],disabled=True)
        with col1:
            numDocumento = st.text_input("Numero Documento", value=listadoClinetes.iloc[st.session_state.indiceTabla, 1])
        with col2:
            nombre = st.text_input("Nombre", key=1, value=listadoClinetes.iloc[st.session_state.indiceTabla, 2])
        with col3:
            direccion = st.text_input("Direccion", key=2, value=listadoClinetes.iloc[st.session_state.indiceTabla, 3])
        with col4:
            telefono = st.text_input("Telefono", key=3, value=listadoClinetes.iloc[st.session_state.indiceTabla, 4])
        with col5:
            email = st.text_input("Email", key=4, value=listadoClinetes.iloc[st.session_state.indiceTabla, 5])
        with col6:
            ciudad = st.text_input("Ciudad", key=5, value=listadoClinetes.iloc[st.session_state.indiceTabla, 6])
        with col7:
            departamento = st.text_input("Departamento", key=6, value=listadoClinetes.iloc[st.session_state.indiceTabla, 7])

        if st.button("Modificar Cliente"):
            # Validar campos requeridos
            if not idCliente or not numDocumento or not nombre or not direccion:
                st.error("Por favor, completa todos los campos requeridos.")
            else:
                # Estructura del cuerpo del POST
                payload = {
                    "id": idCliente,
                    "numeroDocumento": numDocumento,
                    "nombre": nombre,
                    "direccion": direccion,
                    "telefono": telefono,
                    "email": email,
                    "ciudad": ciudad,
                    "departamento": departamento
                }
                try:
                    response = requests.put(f"{api_clientes}/{p_id}", json=payload)

                    # Manejar respuesta
                    if response.status_code == 200:
                        st.session_state.indiceTabla = 0
                        st.success("Cliente modificado exitosamente.")
                    else:
                        st.error(f"Error al modificar el cliente: {response.status_code} - {response.text}")
                except requests.exceptions.RequestException as e:
                    st.error(f"Error de conexión: {e}")

    # --- BORRAR CLIENTE ---
    st.subheader("Eliminar un Cliente:")
    bandera2 = False; indiceTabla2 = 0; count2 = False
    col1, col2, col3 = st.columns([1,0.6,6])
    with col1:
        p_id2 = st.text_input("Id del cliente a borrar")
    with col2:
        st.markdown("<div style='margin-top: 2rem; text-align: center;'>", unsafe_allow_html=True)  # Ajuste de alineación
        if st.button("Eliminar",key=""):
            count2 = True
            if p_id2 in listadoClinetes.iloc[:, 0].values:
                bandera2 = True; 
                try:
                    responseD = requests.delete(api_clientes+f"/{p_id2}")
                except requests.exceptions.RequestException as e:
                    st.error(f"Error de conexión: {e}")
    with col3:
        if not bandera2 and count2:
            st.markdown("<div style='margin-top: 1rem; text-align: center;'>", unsafe_allow_html=True)
            st.error("No se pudo borrar el cliente")
        elif bandera2:
            st.markdown("<div style='margin-top: 1rem; text-align: center;'>", unsafe_allow_html=True)
            st.success("Cliente borrado exitosamente.")
        st.write("")

    # --- OBTENER CLIENTES ---
    col1, col2 = st.columns([1,3.4])
    with col1:
        st.subheader("Obtener los Clientes:")
    with col2:
        if st.button("Actualizar"):
            st.rerun()

    st.dataframe(listadoClinetes, use_container_width=True,hide_index=True) 

# -- TAB PRODUCTOS ---
with tab2:
    # --- CREAR PRODUCTOS ---
    st.subheader("Crear un Producto:")

    col0,col1, col2, col3, col4, col5, col6 = st.columns(7)
    # Columnas de los datos a actulizar
    with col0:
        idProductos = st.text_input("Id Producto")
    with col1:
        codigo = st.text_input("Codigo")
    with col2:
        descripcion = st.text_input("Descripcion")
    with col3:
        precioVenta = st.text_input("Precio Venta")
    with col4:
        impuestoId = st.text_input("Impuesto Id")
    with col5:
        medida = st.text_input("Medida")
    with col6:
        categoriaId = st.text_input("categoriaId")

    if st.button("Crear Producto"):
        # Validar campos requeridos
        if not idProductos or not codigo or not descripcion or not precioVenta:
            st.error("Por favor, completa todos los campos requeridos.")
        else:
            # Estructura del cuerpo del POST
            payload = {
                "id": idProductos,
                "codigo": codigo,
                "descripcion": descripcion,
                "precioVenta": precioVenta,
                "impuestoId": impuestoId,
                "medida": medida,
                "categoriaId": categoriaId
            }

            try:
                response = requests.post(api_productos+"/add", json=payload)
                
                if response.status_code == 200:
                    st.success("Producto agregado exitosamente.")
                else:
                    st.error(f"Error al agregar el producto: {response.status_code} - {response.text}")
            except requests.exceptions.RequestException as e:
                st.error(f"Error de conexión: {e}")

    # --- MODIFICAR PRODUCTO ---
    st.subheader("Modificar un Producto:")

    # Inicializar estado persistente
    if "banderas" not in st.session_state:
        st.session_state.banderas = False
    if "indiceTablas" not in st.session_state:
        st.session_state.indiceTablas = -1
    if "counts" not in st.session_state:
        st.session_state.counts = False

    col1, col2, col3 = st.columns([1, 0.5, 6])

    with col1:
        p_id = st.text_input("Id del producto a cambiar")
    with col2:
        st.markdown("<div style='margin-top: 1.6rem; text-align: center;'>", unsafe_allow_html=True)
        if st.button("Buscar",key=8):
            st.session_state.counts = True
            if p_id in listadoProductos.iloc[:, 0].values:
                st.session_state.banderas = True
                st.session_state.indiceTablas = listadoProductos[listadoProductos.iloc[:, 0] == p_id].index[0]
            else:
                st.session_state.banderas = False

    with col3:
        if not st.session_state.banderas and st.session_state.counts:
            st.markdown("<div style='margin-top: 1rem; text-align: center;'>", unsafe_allow_html=True)
            st.error("Producto no encontrado")

    # Mostrar los campos solo si se encontró un producto
    if st.session_state.banderas:
        st.divider()
        col0, col1, col2, col3, col4, col5, col6 = st.columns(7)
        #, value=listadoProductos.iloc[st.session_state.indiceTablas, 0]
        with col0:
            idProductos = st.text_input("Id Producto", value=listadoProductos.iloc[st.session_state.indiceTablas, 0],disabled=True)
        with col1:
            codigo = st.text_input("Codigo", value=listadoProductos.iloc[st.session_state.indiceTablas, 1])
        with col2:
            descripcion = st.text_input("Descripcion", value=listadoProductos.iloc[st.session_state.indiceTablas, 2])
        with col3:
            precioVenta = st.text_input("Precio Venta", value=listadoProductos.iloc[st.session_state.indiceTablas, 3])
        with col4:
            impuestoId = st.text_input("Impuesto Id", value=listadoProductos.iloc[st.session_state.indiceTablas, 4])
        with col5:
            medida = st.text_input("Medida", value=listadoProductos.iloc[st.session_state.indiceTablas, 5])
        with col6:
            categoriaId = st.text_input("categoriaId", value=listadoProductos.iloc[st.session_state.indiceTablas, 6])

        if st.button("Modificar Producto"):
            # Validar campos requeridos
            if not idProductos or not codigo or not descripcion or not precioVenta:
                st.error("Por favor, completa todos los campos requeridos.")
            else:
                # Estructura del cuerpo del POST
                payload = {
                    "id": idProductos,
                    "codigo": codigo,
                    "descripcion": descripcion,
                    "precioVenta": precioVenta,
                    "impuestoId": impuestoId,
                    "medida": medida,
                    "categoriaId": categoriaId
                }
                try:
                    response = requests.put(f"{api_productos}/{p_id}", json=payload)

                    # Manejar respuesta
                    if response.status_code == 200:
                        st.session_state.indiceTablas = 0
                        st.success("Producto modificado exitosamente.")
                    else:
                        st.error(f"Error al modificar el producto: {response.status_code} - {response.text}")
                except requests.exceptions.RequestException as e:
                    st.error(f"Error de conexión: {e}")

    # --- BORRAR PRODUCTO ---
    st.subheader("Eliminar un Producto:")
    bandera2 = False; indiceTabla2 = 0; count2 = False
    col1, col2, col3 = st.columns([1,0.6,6])
    with col1:
        p_id2 = st.text_input("Id del producto a borrar")
    with col2:
        st.markdown("<div style='margin-top: 2rem; text-align: center;'>", unsafe_allow_html=True)  # Ajuste de alineación
        if st.button("Eliminar",key="a"):
            count2 = True
            if p_id2 in listadoProductos.iloc[:, 0].values:
                bandera2 = True; 
                try:
                    responseD = requests.delete(api_productos+f"/{p_id2}")
                except requests.exceptions.RequestException as e:
                    st.error(f"Error de conexión: {e}")
    with col3:
        if not bandera2 and count2:
            st.markdown("<div style='margin-top: 1rem; text-align: center;'>", unsafe_allow_html=True)
            st.error("No se pudo borrar el producto")
        elif bandera2:
            st.markdown("<div style='margin-top: 1rem; text-align: center;'>", unsafe_allow_html=True)
            st.success("Producto borrado exitosamente.")

    # --- OBTENER PRODUCTOS ---
    col1, col2 = st.columns([1,3])
    with col1:
        st.subheader("Obtener los Productos:")
    with col2:
        if st.button("Actualizar",key=7):
            st.rerun()

    st.dataframe(listadoProductos, use_container_width=True,hide_index=True) 

# --- TAB IMPUESTOS ---
with tab3:
    # --- CREAR PRODUCTOS ---
    st.subheader("Crear un Impuestos:")

    col0, col1, col2 = st.columns(3)
    # Columnas de los datos a actulizar
    with col0:
        idImpuestos = st.text_input("Id Impuestos")
    with col1:
        nombreI = st.text_input("Nombre Impuesto")
    with col2:
        porcentaje = st.text_input("Porcentaje")

    if st.button("Crear Impuesto"):
        # Validar campos requeridos
        if not idImpuestos or not nombreI or not porcentaje:
            st.error("Por favor, completa todos los campos requeridos.")
        else:
            # Estructura del cuerpo del POST
            payload = {
                "id": idImpuestos,
                "nombre": nombreI,
                "porcentaje": porcentaje
            }

            try:
                response = requests.post(api_impuestos+"/add", json=payload)
                
                if response.status_code == 200:
                    st.success("Impuestos agregado exitosamente.")
                else:
                    st.error(f"Error al agregar el impuesto: {response.status_code} - {response.text}")
            except requests.exceptions.RequestException as e:
                st.error(f"Error de conexión: {e}")

    # --- MODIFICAR IMPUESTO ---
    st.subheader("Modificar un Impuesto:")

    # Inicializar estado persistente
    if "banderas1" not in st.session_state:
        st.session_state.banderas1 = False
    if "indiceTablas1" not in st.session_state:
        st.session_state.indiceTablas1 = -1
    if "counts1" not in st.session_state:
        st.session_state.counts1 = False

    col1, col2, col3 = st.columns([1, 0.5, 6])

    with col1:
        p_id = st.text_input("Id del impuesto a cambiar")
    with col2:
        st.markdown("<div style='margin-top: 1.6rem; text-align: center;'>", unsafe_allow_html=True)
        if st.button("Buscar",key=10):
            st.session_state.counts1 = True
            if p_id in listadoImpuestos.iloc[:, 0].values:
                st.session_state.banderas1 = True
                st.session_state.indiceTablas1 = listadoImpuestos[listadoImpuestos.iloc[:, 0] == p_id].index[0]
            else:
                st.session_state.banderas1 = False

    with col3:
        if not st.session_state.banderas1 and st.session_state.counts1:
            st.markdown("<div style='margin-top: 1rem; text-align: center;'>", unsafe_allow_html=True)
            st.error("Impuesto no encontrado")

    # Mostrar los campos solo si se encontró un impuesto
    if st.session_state.banderas1:
        st.divider()
        col0, col1, col2 = st.columns(3)
        #, value=listadoImpuestos.iloc[st.session_state.indiceTablas1, 0]
        with col0:
            idImpuestosM = st.text_input("Id Impuestos", value=listadoImpuestos.iloc[st.session_state.indiceTablas1, 0],disabled=True)
        with col1:
            nombreI = st.text_input("Nombre Impuesto", value=listadoImpuestos.iloc[st.session_state.indiceTablas1, 1])
        with col2:
            porcentaje = st.text_input("Porcentaje", value=listadoImpuestos.iloc[st.session_state.indiceTablas1, 2])

        if st.button("Modificar Impuestos"):
            # Validar campos requeridos
            if not idImpuestos or not nombreI or not porcentaje:
                st.error("Por favor, completa todos los campos requeridos.")
            else:
                # Estructura del cuerpo del POST
                payload = {
                    "id": idImpuestosM,
                    "nombre": nombreI,
                    "porcentaje": porcentaje
                }
                try:
                    response = requests.put(f"{api_impuestos}/{p_id}", json=payload)

                    # Manejar respuesta
                    if response.status_code == 200:
                        st.session_state.indiceTablas1 = 0
                        st.success("Impuesto modificado exitosamente.")
                    else:
                        st.error(f"Error al modificar el impuesto: {response.status_code} - {response.text}")
                except requests.exceptions.RequestException as e:
                    st.error(f"Error de conexión: {e}")

    # --- BORRAR IMPUESTO ---
    st.subheader("Eliminar un Impuesto:")
    bandera2 = False; indiceTabla2 = 0; count2 = False
    col1, col2, col3 = st.columns([1,0.6,6])
    with col1:
        p_id2 = st.text_input("Id del impuesto a borrar")
    with col2:
        st.markdown("<div style='margin-top: 2rem; text-align: center;'>", unsafe_allow_html=True)  # Ajuste de alineación
        if st.button("Eliminar",key="b"):
            count2 = True
            if p_id2 in listadoImpuestos.iloc[:, 0].values:
                bandera2 = True; 
                try:
                    responseD = requests.delete(api_impuestos+f"/{p_id2}")
                except requests.exceptions.RequestException as e:
                    st.error(f"Error de conexión: {e}")
    with col3:
        if not bandera2 and count2:
            st.markdown("<div style='margin-top: 1rem; text-align: center;'>", unsafe_allow_html=True)
            st.error("No se pudo borrar el impuesto")
        elif bandera2:
            st.markdown("<div style='margin-top: 1rem; text-align: center;'>", unsafe_allow_html=True)
            st.success("Impuesto borrado exitosamente.")

    # --- OBTENER PRODUCTOS ---
    col1, col2 = st.columns([1,3])
    with col1:
        st.subheader("Obtener los Impuestos:")
    with col2:
        if st.button("Actualizar",key=9):
            st.rerun()

    st.dataframe(listadoImpuestos, use_container_width=True,hide_index=True) 