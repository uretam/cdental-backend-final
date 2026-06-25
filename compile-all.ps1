# Script de Automatizacion de Compilacion Secuencial - cdental

Write-Output "========================================================="
Write-Output " Iniciando compilacion secuencial de microservicios"
Write-Output "========================================================="

$servicios = @(
    "auth-service",
    "paciente-service",
    "odontologo-service",
    "tratamientos-service",
    "citas-service",
    "pagos-service",
    "historial-service",
    "insumos-service",
    "api-gateway"
)

$raiz = Get-Location

foreach ($servicio in $servicios) {
    Write-Output ""
    Write-Output "[+] Compilando modulo: $servicio ..."
    
    $rutaServicio = Join-Path $raiz $servicio
    
    if (Test-Path $rutaServicio) {
        Set-Location $rutaServicio
        
        $wrapper = Join-Path $rutaServicio "mvnw.cmd"
        if (Test-Path $wrapper) {
            
            # Ejecuta de forma sincrona esperando a que finalice por completo
            $proceso = Start-Process -FilePath ".\mvnw.cmd" -ArgumentList "clean package -DskipTests" -NoNewWindow -PassThru -Wait
            
            if ($proceso.ExitCode -eq 0) {
                Write-Output "[OK] exitoso: $servicio finalizado correctamente."
            } else {
                Write-Output "[ERROR] Fallo la compilacion de $servicio."
                Set-Location $raiz
                Exit $proceso.ExitCode
            }
        } else {
            Write-Output "[WARN] No se encontro mvnw.cmd en $servicio"
        }
    } else {
        Write-Output "[ERROR] La carpeta $servicio no existe."
    }
}

Set-Location $raiz
Write-Output ""
Write-Output "========================================================="
Write-Output " [PROCESO COMPLETADO] Todos los .jar generados en orden."
Write-Output "========================================================="